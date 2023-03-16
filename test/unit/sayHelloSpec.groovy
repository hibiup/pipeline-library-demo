import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification


class sayHelloSpec extends JenkinsPipelineSpecification {
    def sayHello = null

    def setup() {
        /**
         * 单元测试的加载问题：
         *
         * 从 classpath 加载该脚本资源。需要注意两点：
         *
         * 1. 修改 build.gradle 配置文件，将缺省的资源输出目录 Build/classes 改为 target/classes。
         * 2. 有些例子中的参数包含目录 `vars/sayHello.groovy`，这是不必要的，因为 vars 目录被作为 resource 目录的根目录。
         */
        sayHello = loadPipelineScriptForTest("sayHello.groovy")
    }

    def "`sayHello` will"() {
        when:
            sayHello.call('Bob')
        then:
            println('Done!')
    }
}
