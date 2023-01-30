#!/usr/bin/env groovy

import com.cleverbuilder.GlobalVars
import com.cleverbuilder.SampleClass

def call( String name = 'human') {
  echo "Hello, ${name}."

  def sampleClass = new SampleClass()
  sampleClass.setName(GlobalVars.foo)
  sampleClass.setAge(23)

  echo "Happy Birthday! ${sampleClass.getAge()}"
}

