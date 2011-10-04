@Singleton
class SystemOptions {

    private OptionAccessor options

    private CliBuilder cli

    private Map defaultValues = [
            population: 100,
            baseLongevityShortMin: 10,
            baseLongevityShortMax: 200,
            baseLongevityAverageMin: 201,
            baseLongevityAverageMax: 419,
            baseLongevityLongMin: 420,
            baseLongevityLongMax: 620,
            strengthWeakMin: 10,
            strengthWeakMax: 200,
            strengthAverageMin: 201,
            strengthAverageMax: 419,
            strengthStrongMin: 420,
            strengthStrongMax: 620,
            colorPrefNoPrefMin: 201,
            colorPrefNoPrefMax: 419,
            colorPrefPrefMin: 10,
            colorPrefPrefMax: 200,
            colorPrefStrongPrefMin: 420,
            colorPrefStrongPrefMax: 620,
            colorRedMin: 10,
            colorRedMax: 200,
            colorOrangeMin: 201,
            colorOrangeMax: 249,
            colorYellowMin: 250,
            colorYellowMax: 299,
            colorGreenMin: 300,
            colorGreenMax: 310,
            colorBlueMin: 311,
            colorBlueMax: 360,
            colorIndigoMin: 361,
            colorIndigoMax: 419,
            colorVioletMin: 420,
            colorVioletMax: 620,
            windowsColors: false,
    ]

    private Map types = [
            population: Integer,
            baseLongevityShortMin: Integer,
            baseLongevityShortMax: Integer,
            baseLongevityAverageMin: Integer,
            baseLongevityAverageMax: Integer,
            baseLongevityLongMin: Integer,
            baseLongevityLongMax: Integer,
            strengthWeakMin: Integer,
            strengthWeakMax: Integer,
            strengthAverageMin: Integer,
            strengthAverageMax: Integer,
            strengthStrongMin: Integer,
            strengthStrongMax: Integer,
            colorPrefNoPrefMin: Integer,
            colorPrefNoPrefMax: Integer,
            colorPrefPrefMin: Integer,
            colorPrefPrefMax: Integer,
            colorPrefStrongPrefMin: Integer,
            colorPrefStrongPrefMax: Integer,
            colorRedMin: Integer,
            colorRedMax: Integer,
            colorOrangeMin: Integer,
            colorOrangeMax: Integer,
            colorYellowMin: Integer,
            colorYellowMax: Integer,
            colorGreenMin: Integer,
            colorGreenMax: Integer,
            colorBlueMin: Integer,
            colorBlueMax: Integer,
            colorIndigoMin: Integer,
            colorIngigoMax: Integer,
            colorVioletMin: Integer,
            colorVioletMax: Integer,
            windowsColors: Boolean,
    ]

    SystemOptions() {
        String header = """\n
Description
=========================================================================
Hevollo is a super simple life simulator. Insipired by simple 'Hello World' evolutionary algorithms.
+
Organisms exist in populations They only have a few basic attributes: life, strength and color. Each Organism has a dna sequence which is represented as a String of characters (a-zA-Z0-9). These strings are divided up into groups which represent the gene for each trait.
+
Usage
=========================================================================
Other than the options described below.
+
Configure chance of trait
-------------------------------------------------------------------------
There are currently 4 traits: 'baseLongevity', 'strength', 'color', and 'colorPref' coding sequences for each of these traits are 10 characters long. To determine the trait that they code for each character (base) is assiged a number based on it's index (a=1, b=2, ..., A=27, ..., 0=53, ..., 9=62). The minimum value a trait could have is 10 while the maximum value a trait can have is 620. The reason for this is to form a gaussian/normal function with a mean of 315 and a standard deviation of 56.5. The allows us to specify specific ranges and have chance of a specifc encoding be very rare towards the edges and very common towards the center.
+
Options
-------------------------------------------------------------------------
"""
        Map usage = [
                usage: 'Simulation [options]',
                header: header,
                footer: 'Test footer']
        cli = new CliBuilder(usage)

        cli.h longOpt: 'help', 'Prints this help message'
        cli._ longOpt: 'population', args: 1, argName: 'size', 'The size of the initial population. Default is 100'
        cli._ longOpt: 'windowsColors', 'Use this option to hide color escape codes on windows when using one of the console displays. The dos console doesn\'t support ansi escape sequences.'
    }

    void initialize(String[] args) {
        options = cli.parse(args)
        if (options.help) cli.usage()
    }

    Object getAt(String name, Object defaultValue = null) {
        getProperty(name) ?: (defaultValue ?: defaultValues.get(name))
    }

    Object getProperty(String name) {
        Object prop = options.getProperty(name)
        if (prop && types.containsKey(name)) prop.asType(types.get(name))
        else prop
    }

    Boolean isInvalid() {
        return options == null || options.help
    }
}

