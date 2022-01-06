package dev.codescreen

import scala.io.Source

object ControlStructuresHomework1 {
  type Error = String

  // consider person an adult if their age is higher than 18
  // return error message "$age is too high, are you human?" if age is higher than 150
  // return error message "$age is negative, we do not serve unborn people" if age is lower than 0
  // use if-else
  def isAdultIf(age: Int): Either[Error, Boolean] = {
    if (age > 150) {
      Left("$age is too high, are you human?")
    } else if (age < 0) {
      Left("$age is negative, we do not serve unborn people")
    } else {
      Right(age > 18)
    }
  }

  // same as isAdultIf, but use match statement instead
  def isAdultMatch(age: Int): Either[Error, Boolean] = {
    age match {
      case age if age > 150 => Left("$age is too high, are you human?")
      case age if age < 0 => Left("$age is negative, we do not serve unborn people")
      case _ => Right(age > 18)
    }
  }

  // https://en.wikipedia.org/wiki/Triangle_inequality
  // can you do it without using any control structures?
  def isValidTriangle(a: Double, b: Double, c: Double): Boolean = {
    (a + b < c) && (a + c < b) && (b + c < a)
  }

  // IT company located in Wakanda is searching for a new programmer. Due to high interest it needs
  // a way to filter out candidates that are not suitable for this job.
  // They have a number of characteristics that give candidates points, and valid candidate
  // should earn at least 10 points.
  //
  // * Each year of experience gives candidate 1 point, but not more than 5
  // * If candidate has education, it gives him 3 points
  // * Each passed test gives candidate 1 point after 5 passed tests.
  //   If candidate has passed less than 5 tests, they don't want to hire him in any case.
  // * They prefer candidates from their country, so being from Wakanda gives candidate 3 points.
  //   If the candidate is from any of the neighboring countries - Narnia, Skyrim or Amestris -
  //   candidate should get 1 point, otherwise - 0
  // * Summed stars on github also give points.
  //   1 point if candidate has 10 stars, 2 points for 100, 3 points for 1000 and so on
  //
  // All input is valid, e.g. candidate can't have negative years of experience
  def isValidCandidate(
    country: String,
    passedTests: Int,
    yearsOfExperience: Int,
    hasEducation: Boolean,
    starsOnGithub: Int
  ): Boolean = {
    var result = 0
    if (yearsOfExperience > 5) {
      result += 5
    } else {
      result += yearsOfExperience
    }
    if (hasEducation) {
      result += 3
    }
    if (passedTests < 5) {
      false
    } else {
      result += passedTests
    }
    country match {
      case "Wakanda" => result += 3
      case "Narnia" | "Skyrim" | "Amestris" => result += 1
    }

    starsOnGithub match {
      case stars if stars > 1000 => result += 3
      case stars if stars > 100 => result += 2
      case stars if stars > 10 => result += 1
    }

    result >= 10
  }
}
