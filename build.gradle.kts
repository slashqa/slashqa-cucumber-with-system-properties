plugins {
    java
}


dependencies {
    testImplementation("io.cucumber:cucumber-java:7.11.2")
    testImplementation("io.cucumber:cucumber-picocontainer:7.11.2") // added for DI
    testImplementation("io.cucumber:cucumber-junit:7.11.2") // added for assertions
}

repositories {
    mavenLocal()
    mavenCentral()
}

configurations {
    register("cucumberRuntime") {
        extendsFrom(testImplementation.get())
    }
}

val propOne=project.properties["propOne"]
tasks {
    val runTest by registering(Test::class) {
        doLast {
            runCucumber()
        }
        this.testLogging.showStandardStreams = true
        outputs.upToDateWhen {false}
    }
    
    val outputPropertyTest by registering{
        doLast{
            println("Passed in property $propOne")
        }
    }
}


fun runCucumber(){
    javaexec {
        val main = "io.cucumber.core.cli.Main"
        val cucumberRuntime = configurations["cucumberRuntime"]
        val threads = kotlin.math.floor(Runtime.getRuntime().availableProcessors().div(2).toDouble()).toInt().toString()
        mainClass.set(main)
        classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output

        environment("CUCUMBER_PUBLISH_QUIET", true)

        args = mutableListOf(
                "--threads", threads,
                "--plugin", "pretty",
                "--plugin", "html:target/cucumber-report.html"
        )
        args(args)

        // Set any System Properties
        systemProperty("prop.one", propOne)
    }
}