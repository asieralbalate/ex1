package exemples

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    var f = File.listRoots()[0]
    println(llistaDirectori(f))
    var noAcaba = true
    var currentFile: File
    while (noAcaba) {
        println("Introdueix un numero (-1 per acabar): ")
        var entrada = BufferedReader(InputStreamReader(System.`in`)).readLine()
        var numero = entrada.toInt()
        if (numero == -1) {
            System.exit(0)
        } else if (numero > f.listFiles().size || numero < -1) {
            println("Numero incorrecte")
        } else {
            if (numero == 0) {
                if (f.parentFile.exists() == null) {
                    println(llistaDirectori(f))
                }
            } else {
                currentFile = f.listFiles().sorted()[numero - 1]
                f = currentFile
                if (currentFile.isDirectory) {
                    if (currentFile.canRead()) {
                        println(llistaDirectori(currentFile))
                    } else {
                        println("No tens permisos")
                    }
                }
            }
        }
    }
}

fun llistaDirectori(f: File) {
    val s = "Llista de fitxers i directoris del directori " + f.path
    var n = 1
    println(s)
    println("-".repeat(s.length))
    println("${0}.- " + f.parent)
    for (e in f.listFiles().sorted()) {
        if (e.isFile())
            println("${n}.- " + e.getName() + "\t " + e.length())
        if (e.isDirectory()) {
            println("${n}.- " + e.getName() + "\t <Directori>")
        }
        n++
    }
}


