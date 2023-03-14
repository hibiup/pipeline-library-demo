#!/usr/bin/env groovy

/*
  这是 pipeline 脚本，可以直接 `import` /src下的代码：
*/

import com.cleverbuilder.GlobalVars
import com.cleverbuilder.SampleClass

/**
 * 入口函数
 */
def call( String name = 'human') {
  echo "Hello, ${name}."

  // 可以调用其它定义在 src 目录下的内部资源。
  def sampleClass = new SampleClass()
  sampleClass.setName(GlobalVars.foo)
  sampleClass.setAge(23)

  echo "Happy Birthday! ${sampleClass.getAge()}"
}

