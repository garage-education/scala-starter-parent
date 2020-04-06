package com.gability.labs.cicd

object Utils {

  /**
    * This function counts the words in a given string.
    *
    * @param s represents the input string we are going t count its words.
    * @return integer representing the word count in the input string.
    */
  def workCount(s: String): Int = {
    s match {
      case str if isEmpty(str) => 0
      case str                 => str.split("\\W+").length
    }
  }

  /**
    * This function checks if the input string is not null or empty.
    *
    * @param x represents the input string we are going to check.
    * @return true if the input string is null or empty otherwise returns false.
    */
  private def isEmpty(x: String) = x == null || x.trim.isEmpty
}
