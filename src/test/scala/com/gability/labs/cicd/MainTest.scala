package com.gability.labs.cicd

import com.gability.labs.cicd.Utils._
import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite {
  test("Test wordCount Function with input string") {
    val inputSentence = "Testing Word Count Func"
    assert(workCount(inputSentence) == 4)
  }
  // commented for testing some scenarios to break the test coverage.
  /*  test("Test wordCount Function with null input") {
    val inputSentence = null
    assert(workCount(inputSentence) == 0)
  }*/
}
