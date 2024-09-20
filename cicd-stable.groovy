node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/cunitport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/cunitport.git'), string(name: 'PORT_DESCRIPTION', value: 'CUnit is a Unit testing framework for C.' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
