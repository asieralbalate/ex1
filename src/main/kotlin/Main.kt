package exemples

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import javax.naming.spi.DirectoryManager

fun main(args: Array<String>) {
    val f = File.listRoots()[0]
    val result = listDirectory(f)
    println(result)
}

fun listDirectory(f: File) {
    val s = "Llista de fitxers i directoris del directori " + f.path
    var n = 1
    println(" ")
    println(s)
    println("-".repeat(s.length))
    if (f.parent == null) {
        println("0.- Directori pare")
    } else {
        println("0.- ${f.parent}")
    }
    for (e in f.listFiles().sorted()) {
        if (e.isFile())
            println("${n}.- " + e.getName() + "\t " + e.length())
        if (e.isDirectory()) {
            println("${n}.- " + e.getName() + "\t <Directori>")
        }
        n++
    }
    introduceNumber(f)
}

private fun introduceNumber(f: File) {
    println(" ")
    println("Introdueix un numero (-1 per acabar): ")
    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val num = input.toIntOrNull()
    if (num != null) {
        loopNumbers(num, f)
    } else {
        println("Entrada no valida. Introduix un numero válid.")
        println("")
        listDirectory(f)
    }
}

fun loopNumbers(number: Int, f: File) {
    val currentFile: File
    if (number == -1) {
        System.exit(0)
    } else if (number > f.listFiles().size || number < -1) {
        println("Numero incorrecte")
        println(listDirectory(f))
    } else if (number == 0) {
        if (f.parentFile != null && f.parentFile.exists()) {
            currentFile = f.parentFile
            println(listDirectory(currentFile))
        } else {
            println(listDirectory(f))
        }
    } else {
        currentFile = f.listFiles().sorted()[number - 1]
        if (currentFile.canRead() && currentFile.canWrite() && currentFile.canExecute() && currentFile.isDirectory) {
            println(listDirectory(currentFile))
        } else {
            if (!currentFile.canRead()) {
                println("No tens permisos")
                println(listDirectory(f))
            } else {
                println(listDirectory(f))
            }
        }
    }
}