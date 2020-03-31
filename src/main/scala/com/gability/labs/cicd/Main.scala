package com.gability.labs.cicd

object Main extends App {
  def workCount(s:String): Int = {
    s match {
      case str if isEmpty(str) => 0
      case str => str.split("\\W+").length
    }
  }
  def isEmpty(x: String) = x == null || x.trim.isEmpty
}

