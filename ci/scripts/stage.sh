#!/bin/bash
set -e

source $(dirname $0)/common.sh

set_revision() {
  [[ -n $1 ]] || { echo "missing set_revision_to_pom() argument" >&2; return 1; }
  grep -q "<revision>.*</revision>" pom.xml || { echo "missing revision tag" >&2; return 1; }
  sed --in-place -e "s|<revision>.*</revision>|<revision>${1}</revision>|" pom.xml > /dev/null
}

repository=$(pwd)/distribution-repository

pushd git-repo > /dev/null
git fetch --tags --all > /dev/null
popd > /dev/null

git clone git-repo stage-git-repo > /dev/null

pushd stage-git-repo > /dev/null

snapshotVersion=$( get_revision_from_pom )
if [[ $RELEASE_TYPE = "M" ]]; then
	stageVersion=$( get_next_milestone_release $snapshotVersion)
	nextVersion=$snapshotVersion
elif [[ $RELEASE_TYPE = "RC" ]]; then
	stageVersion=$( get_next_rc_release $snapshotVersion)
	nextVersion=$snapshotVersion
elif [[ $RELEASE_TYPE = "RELEASE" ]]; then
	stageVersion=$( get_next_release $snapshotVersion "RELEASE" )
	nextVersion=$( bump_version_number $snapshotVersion)
else
	echo "Unknown release type $RELEASE_TYPE" >&2; exit 1;
fi

echo "Staging $stageVersion (next version will be $nextVersion)"

set_revision "$stageVersion"
git config user.name "Spring Buildmaster" > /dev/null
git config user.email "buildmaster@springframework.org" > /dev/null
git add pom.xml > /dev/null
git commit -m"Release v$stageVersion" > /dev/null
git tag -a "v$stageVersion" -m"Release v$stageVersion" > /dev/null

run_maven -f spring-boot-project/pom.xml clean deploy -U -Dfull -DaltDeploymentRepository=distribution::default::file://${repository} -Dgradle.cache.local.enabled=false -Dgradle.cache.remote.enabled=false -Duser.name=concourse
run_maven -f spring-boot-samples/pom.xml clean install -U -Dfull -Drepository=file://${repository} -Dgradle.cache.local.enabled=false -Dgradle.cache.remote.enabled=false -Duser.name=concourse
run_maven -f spring-boot-tests/spring-boot-integration-tests/pom.xml clean install -U -Dfull -Drepository=file://${repository} -Dgradle.cache.local.enabled=false -Dgradle.cache.remote.enabled=false -Duser.name=concourse
run_maven -f spring-boot-tests/spring-boot-deployment-tests/pom.xml clean install -U -Dfull -Drepository=file://${repository} -Dgradle.cache.local.enabled=false -Dgradle.cache.remote.enabled=false -Duser.name=concourse

git reset --hard HEAD^ > /dev/null
if [[ $nextVersion != $snapshotVersion ]]; then
	echo "Setting next development version (v$nextVersion)"
	set_revision "$nextVersion"
	git add pom.xml > /dev/null
	git commit -m"Next development version (v$nextVersion)" > /dev/null
fi;

echo "DONE"

popd > /dev/null
