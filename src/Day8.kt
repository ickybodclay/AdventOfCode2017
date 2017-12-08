import java.io.BufferedReader
import java.io.FileReader
import java.util.regex.Pattern

class Day8 {
    data class Instruction(
            val register: String,
            val operation: String,
            val amount: Int,
            val conditionRegister: String,
            val condition: String,
            val conditionAmount: Int)

    private val instructionPattern: Pattern = Pattern.compile("""(\w+) (inc|dec) (-?\d+) if (\w+) (>|>=|<|<=|==|!=) (-?\d+)""")

    private fun readInput() : List<Instruction> {
        val instructionList = mutableListOf<Instruction>()
        val reader = BufferedReader(FileReader("day8_input.txt"))
        reader.readLines().map {
            val matcher = instructionPattern.matcher(it)
            if (matcher.matches()) {
                instructionList.add(
                        Instruction(
                                matcher.group(1),
                                matcher.group(2),
                                Integer.parseInt(matcher.group(3)),
                                matcher.group(4),
                                matcher.group(5),
                                Integer.parseInt(matcher.group(6))))
            }
        }
        return instructionList
    }

    fun printSolution() {
        val instructionList = readInput()
        println("instructions = " + instructionList)
        val registerMap = mutableMapOf<String, Int>()
        var maxValueEver = 0
        instructionList.map {
            if (!registerMap.containsKey(it.register)) {
                registerMap[it.register] = 0
            }

            if (!registerMap.containsKey(it.conditionRegister)) {
                registerMap[it.conditionRegister] = 0
            }

            if (checkCondition(registerMap, it)) {
                performInstruction(registerMap, it)

                val maxRegisterNow = registerMap.maxBy { it.value }
                if (maxRegisterNow!!.value > maxValueEver) {
                    maxValueEver = maxRegisterNow.value
                }
            }
        }

        println("[Part 1] highest value register = ${registerMap.maxBy { it.value }}")

        println("[Part 2] largest value ever = $maxValueEver")
    }

    private fun checkCondition(registerMap: Map<String, Int>, instruction :Instruction) : Boolean {
        when(instruction.condition) {
            "<" -> return registerMap[instruction.conditionRegister]!! < instruction.conditionAmount
            ">" -> return registerMap[instruction.conditionRegister]!! > instruction.conditionAmount
            "<=" -> return registerMap[instruction.conditionRegister]!! <= instruction.conditionAmount
            ">=" -> return registerMap[instruction.conditionRegister]!! >= instruction.conditionAmount
            "==" -> return registerMap[instruction.conditionRegister]!! == instruction.conditionAmount
            "!=" -> return registerMap[instruction.conditionRegister]!! != instruction.conditionAmount
        }
        return false
    }

    private fun performInstruction(registerMap: MutableMap<String, Int>, instruction :Instruction) {
        when(instruction.operation) {
            "inc" -> registerMap[instruction.register] = registerMap[instruction.register]!! + instruction.amount
            "dec" -> registerMap[instruction.register] = registerMap[instruction.register]!! - instruction.amount
        }
    }
}

fun main(args: Array<String>) {
    Day8().printSolution()
}