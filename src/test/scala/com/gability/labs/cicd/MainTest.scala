package com.gability.labs.cicd

import com.gability.labs.cicd.Main._
import org.scalatest.funsuite.AnyFunSuite
class MainTest extends AnyFunSuite {
  test("Test wordCount Function with input string") {
    val inputSentence = "Testing Word Count Func"
    assert(wordCount(inputSentence) == 4)
  }

  test("Test wordCount Function with null input") {
    val inputSentence = null
    assert(wordCount(inputSentence) == 0)
  }
}
