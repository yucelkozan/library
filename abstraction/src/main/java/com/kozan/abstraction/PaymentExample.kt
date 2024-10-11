package com.kozan.abstraction


interface PaymentMethod {
    fun makePayment(amount: Double)
}

class CreditCard : PaymentMethod {
    override fun makePayment(amount: Double) {
        println("Paid $amount using credit card")
    }

}

class Cash : PaymentMethod {
    override fun makePayment(amount: Double) {
        println("Paid $amount in Cash.")
    }

}


fun main1() {
    val creditCardPayment = CreditCard()
    creditCardPayment.makePayment(100.0)

    val cashPayment = Cash()
    cashPayment.makePayment(50.0)
}


/*
*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

// ortak olan işlevler abstract classda, ekstra özellikler interface olarak
abstract class PaymentMethod2 {
    abstract fun makePayment(amount: Double)
    fun startTransaction() {
        println("Transaction started")
    }

    fun endTransaction() {
        println("Transaction ended")
    }

}

// bütün odemelerde olmayan, istisnai ek bir özellik olduğu için interface olarak yapıldı.
interface BiometricAuth {
    fun authenticateWithBiometrics()
    fun interfaceFuncWithBody(i: Int) {
        println("function with body result = ${i * i}")
    }
}

class CreditCardPayment : PaymentMethod2() {
    override fun makePayment(amount: Double) {
        println("Processing CreditCard payment of $amount")
    }
}

class PayPalPayment : PaymentMethod2() {
    override fun makePayment(amount: Double) {
        println("Processing PayPal payment of $amount")
    }

}

class ApplePayPayment : PaymentMethod2(), BiometricAuth {
    override fun makePayment(amount: Double) {
        println("Processing ApplePay payment of $amount")
    }

    override fun authenticateWithBiometrics() {
        println("Authanticated with FaceId")
    }

    // bodysi olana function istersen kendin implement ediyorsun.
    override fun interfaceFuncWithBody(i: Int) {
        super.interfaceFuncWithBody(i) // implement ettiğimde  super kısmı kendi otomatik geliyor.
        // metodu bir kere çalıştıyor. metodun içi boşsa veya çalışmasını istemiyorsan silebilirsin.
        // istersen silabilirsin.
        println("funcWithBody in ApplePayPayment")
    }


}

fun main2() {
    val creditCardPayment = CreditCardPayment()
    creditCardPayment.startTransaction()
    creditCardPayment.makePayment(500.4)
    creditCardPayment.endTransaction()

    val payPalPayment = PayPalPayment()
    payPalPayment.startTransaction()
    payPalPayment.makePayment(300.9)
    payPalPayment.endTransaction()


    val applePayPayment = ApplePayPayment()
    applePayPayment.startTransaction()
    applePayPayment.authenticateWithBiometrics()//interface
    applePayPayment.makePayment(1000.45)
    applePayPayment.endTransaction()
    applePayPayment.interfaceFuncWithBody(5)

}


/**
 *  Basic Interface
 *
 *  A Kotlin interface contains declarations of abstract methods, and default method implementations although they cannot store state.
 */

interface MyInterface1 {
    fun bar()
}

/**
 * This interface can now be implemented by a class as follows:
 * */

class Child : MyInterface1 {
    override fun bar() {
        print("bar() was called")
    }
}

/**
 * Interface with Default Implementations
 * An interface in Kotlin can have default implementations for functions:
 * */

interface MyInterface2 {
    fun withImplementation() {
        print("withImplementation() was called")
    }
}

/**
Classes implementing such interfaces will be able to use those functions without reimplementing
 */

class MyClass2 : MyInterface2 {
    // No need to reimplement here
}





class MyClass3 : MyInterface2 {
    // No need to reimplement here

    // Eğer interface'deki metod bir varsayılan implemantasyona sahipse, super anahtar kelimesiyle bu varsayılan implemantasyona erişebilirsin.
    override fun withImplementation() {
        super.withImplementation()   // implment edince otomatik super ile böyle geliyor. // default imlementasyonu kullanmak istemiyorsan silebilirsin
        // default implemntasyona ek olarak bir şey yapabilirsin
    }
}

fun main(){
    val instance2 = MyClass2()
    instance2.withImplementation()
}

