@Singleton
class StartOptions {

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

    StartOptions() {
        Map usage = [
                usage: 'Simulation [options]',
                header: 'Test header',
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

