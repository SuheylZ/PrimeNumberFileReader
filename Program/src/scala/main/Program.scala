

object Program {

  def main(args: Array[String])= {

    println(welcomeMessage)

    val primes = PrimeNumberProvider("D:\\Projects\\MyWorld\\Program\\data").getPrimes(40).toVector
    println(primes)

    println(goodbyeMessage)
  }

   val welcomeMessage =
      """
        Welcome to random code generator. This program will generate codes based on the input values.
        To get more info use cb -help to see the optiona available
      """

   val goodbyeMessage = "Thank you for using the program. Good bye!"

 }
