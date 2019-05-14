import java.io.File
import java.util

import org.apache.commons.io.FileUtils

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.{Failure, Success, Try}


class PrimeNumberProvider(val directory: String) {
  private val _primes = new ArrayBuffer[BigInt]()
  fillPrimes()

  private def getAllFileNamesFromDirectory(path: String): Option[List[String]] = {

    val f = new File(path)

    if(f.exists && f.isDirectory){
      Some( f.listFiles(x=> x.isFile && x.getName.contains(".txt")).map(_.getAbsolutePath).toList )
    }
    else
      None

  }
  private def fillPrimes(): Unit = {

    getAllFileNamesFromDirectory(directory) match {
      case Some(files)=> files.foreach(file => readFile(file).foreach(_primes.append(_)))
      case None => println("")
    }

  }


  private def readFile(file: String): Vector[BigInt] =  {
    var primes  = new ArrayBuffer[BigInt]()
    var mylines = FileUtils.readLines(new File(file), "UTF-8").iterator()

    while(mylines.hasNext){
      primes ++= mylines.next().split(Array(' ', ',', '\n', '\t')).map(BigInt(_)).toSeq
    }

    primes.toVector
  }

  def getPrimes(howMany: Int) : Vector[BigInt] = { _primes.take(howMany).toVector }

}


object PrimeNumberProvider{
  def apply(path: String): PrimeNumberProvider ={
    new PrimeNumberProvider(path)
  }
}
