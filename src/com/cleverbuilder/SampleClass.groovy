#!/usr/bin/env groovy
package com.cleverbuilder

class SampleClass {
   String name
   Integer age

   def increaseAge(Integer years) {
      println("Increase $years year(s)")
      this.age += years
   }
}
