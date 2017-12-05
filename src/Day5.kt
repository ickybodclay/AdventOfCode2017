import java.io.BufferedReader
import java.io.FileReader

class Day5 {
    private fun readInput() : MutableList<Int> {
        val reader = BufferedReader(FileReader("day5_input.txt"))
        val instructionArray = mutableListOf<Int>()
        reader.forEachLine {
            instructionArray.add(it.toInt())
        }
        //println("instructions = " + instructionArray)
        return instructionArray
    }

    fun printPart1Solution() {
        val instructionArray = readInput()
        var index = 0;
        var steps = 0;
        while (index >= 0 && index < instructionArray.size) {
            val newIndex = index + instructionArray[index]
            instructionArray[index]++
            index = newIndex
            steps++
        }

        println("# of steps = " + steps)
    }
}

fun main(args: Array<String>) {
    Day5().printPart1Solution()
}