package me.bendoerr.hevollo

@Singleton
class SystemOptions {
    static final String OPTIONS_USAGE = "Simulation.groovy [options]"
    static final String OPTIONS_USAGE_HEADER = """
${'==Description'.padRight(20,'=')}
Hevollo is a super simple life simulator. Insipired by simple 'Hello World' evolutionary algorithms/programs.

Organisms exist in populations They only have a few basic attributes: life, strength and color. Each Organism has a dna sequence which is represented as a String of characters (a-zA-Z0-9). These strings are divided up into groups which represent the gene for each trait.

${'==Usage/Details'.padRight(20, '=')}
There are currently 4 genes: 'baseLongevity', 'strength', 'color', and 'colorPref'. Coding sequences for each of these genes are 10 characters long. To determine the trait that they code for each character (base) is assiged a number based on it's index (a=1, b=2, ..., A=27, ..., 0=53, ..., 9=62). The minimum value a trait could have is 10 while the maximum value a trait can have is 620. When bases are assigned randomly the distribution of trait values is a guassian/normal function with a mean of 315 and a standard deviation of 56.5. The allows us to specify specific ranges and have chance of a specifc encoding be very rare towards the edges and very common towards the center.

${'==Options'.padRight(20, '=')}

"""
    static final String OPTIONS_USAGE_FOOTER = ""

    private OptionAccessor options

    private CliBuilder cli

    private Map availableOptions = [
            population: [defaultValue: 100, type: Integer, short: 'p', args: 1, argName: 'size'],
            baseLongevityShortMin: [defaultValue: 10, type: Integer, args: 1, argName: 'bound'],
            baseLongevityShortMax: [defaultValue: 200, type: Integer, args: 1, argName: 'bound'],
            baseLongevityAverageMin: [defaultValue: 201, type: Integer, args: 1, argName: 'bound'],
            baseLongevityAverageMax: [defaultValue: 419, type: Integer, args: 1, argName: 'bound'],
            baseLongevityLongMin: [defaultValue: 420, type: Integer, args: 1, argName: 'bound'],
            baseLongevityLongMax: [defaultValue: 620, type: Integer, args: 1, argName: 'bound'],
            strengthWeakMin: [defaultValue: 10, type: Integer, args: 1, argName: 'bound'],
            strengthWeakMax: [defaultValue: 200, type: Integer, args: 1, argName: 'bound'],
            strengthAverageMin: [defaultValue: 201, type: Integer, args: 1, argName: 'bound'],
            strengthAverageMax: [defaultValue: 419, type: Integer, args: 1, argName: 'bound'],
            strengthStrongMin: [defaultValue: 420, type: Integer, args: 1, argName: 'bound'],
            strengthStrongMax: [defaultValue: 620, type: Integer, args: 1, argName: 'bound'],
            colorPrefNoPrefMin: [defaultValue: 201, type: Integer, args: 1, argName: 'bound'],
            colorPrefNoPrefMax: [defaultValue: 419, type: Integer, args: 1, argName: 'bound'],
            colorPrefPrefMin: [defaultValue: 10, type: Integer, args: 1, argName: 'bound'],
            colorPrefPrefMax: [defaultValue: 200, type: Integer, args: 1, argName: 'bound'],
            colorPrefStrongPrefMin: [defaultValue: 420, type: Integer, args: 1, argName: 'bound'],
            colorPrefStrongPrefMax: [defaultValue: 620, type: Integer, args: 1, argName: 'bound'],
            colorRedMin: [defaultValue: 10, type: Integer, args: 1, argName: 'bound'],
            colorRedMax: [defaultValue: 200, type: Integer, args: 1, argName: 'bound'],
            colorOrangeMin: [defaultValue: 201, type: Integer, args: 1, argName: 'bound'],
            colorOrangeMax: [defaultValue: 249, type: Integer, args: 1, argName: 'bound'],
            colorYellowMin: [defaultValue: 250, type: Integer, args: 1, argName: 'bound'],
            colorYellowMax: [defaultValue: 299, type: Integer, args: 1, argName: 'bound'],
            colorGreenMin: [defaultValue: 300, type: Integer, args: 1, argName: 'bound'],
            colorGreenMax: [defaultValue: 310, type: Integer, args: 1, argName: 'bound'],
            colorBlueMin: [defaultValue: 311, type: Integer, args: 1, argName: 'bound'],
            colorBlueMax: [defaultValue: 360, type: Integer, args: 1, argName: 'bound'],
            colorIndigoMin: [defaultValue: 361, type: Integer, args: 1, argName: 'bound'],
            colorIndigoMax: [defaultValue: 419, type: Integer, args: 1, argName: 'bound'],
            colorVioletMin: [defaultValue: 420, type: Integer, args: 1, argName: 'bound'],
            colorVioletMax: [defaultValue: 620, type: Integer, args: 1, argName: 'bound'],
            windowsColors: [defaultValue: false, type: Boolean, desc: 'Disable colors for windows'],
    ]

    SystemOptions() {
        cli = new CliBuilder(usage: OPTIONS_USAGE, header: OPTIONS_USAGE_HEADER, footer: OPTIONS_USAGE_FOOTER)
        cli.formatter = new HelpFormatter()
        cli.width = 110

        cli.h longOpt: 'help', 'Prints this help message'

        availableOptions.each {k,v->
            Map usageMap = [longOpt: k]
            if(v.args) usageMap.args = v.args
            if(v.argName) usageMap.argName = v.argName

            String desc = v.desc ?: "Default Value: ${v.defaultValue}, ".padRight(20) + "Type: ${v.type.getSimpleName()}"

            if(v.short) {
                cli."${v.short}"(usageMap, desc)
            } else {
                cli._(usageMap, desc)
            }
        }
    }

    void initialize(String[] args) {
        options = cli.parse(args)
        if (options.help) cli.usage()
    }

    Object getAt(String name, Object defaultValue = null) {
        getProperty(name) ?: (defaultValue ?: availableOptions.get(name).defaultValue)
    }

    Object getProperty(String name) {
        if(name == 'cli') return cli

        Object prop = options.getProperty(name)

        if (prop && availableOptions.containsKey(name)) {
            return prop.asType(availableOptions.get(name).type)
        }

        return prop
    }

    Boolean isInvalid() {
        return options == null || options.help
    }
}

