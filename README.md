# pipeline-library-demo

Demonstrates how to use a Shared Library in Jenkins pipelines. This repository defines a single function, `sayHello`, 
which will echo a greeting.

## Setup instructions

1. In Jenkins, go to Manage Jenkins &rarr; Configure System. Under _Global Pipeline Libraries_, add a library with 
the following settings:

    - Name: `pipeline-library-demo`
    - Default version: Specify a Git reference (branch or commit SHA), e.g. `master`
    - Retrieval method: _Modern SCM_
    - Select the _Git_ type
    - Project repository: `https://github.com/monodot/pipeline-library-demo.git`
    - Credentials: (leave blank)

2. Then create a Jenkins job with the following pipeline (note that the underscore `_` is not a typo):

    ```
    // 加载共享库. `pipeline-library-demob` 是配置的库名，`@<branch_name>` 为可选项。
    @Library('pipeline-library-demob@master')_

    stage('Demo') {
      echo 'Hello World'
   
      // 调用共享库。共享入口脚本必须保存在 vars 目录下。函数名就是脚本文件名，入口方法名称是 `call`，可以接受参数。
      sayHello 'Dave'
    }
    ```

This will output the following from the build:

```
[Pipeline] stage
[Pipeline] { (Demo)
[Pipeline] echo
Hello world
[Pipeline] echo
Hello, Dave.
[Pipeline] }
[Pipeline] // stage
[Pipeline] End of Pipeline
Finished: SUCCESS
```

