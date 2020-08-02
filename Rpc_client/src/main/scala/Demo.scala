class Demo {

}

import java.lang.reflect.Method


object MethodDemo {
  def main(args: Array[String]): Unit = {
    val methods = classOf[SampleClass].getMethods
    println(methods)
    val parameterTypes = methods(1).getParameterTypes

    for (parameterType <- parameterTypes) {
      println(parameterType.getName)
    }
  }
}

class SampleClass {
  private var sampleField = null

  def getSampleField: String = sampleField

  def setSampleField(sampleField: String): Unit = {
    this.sampleField = sampleField.asInstanceOf
  }

}

