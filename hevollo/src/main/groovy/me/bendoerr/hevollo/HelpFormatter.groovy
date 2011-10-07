package me.bendoerr.hevollo

import org.apache.commons.cli.HelpFormatter as CommonsHelpFormatter
import groovy.transform.InheritConstructors

@InheritConstructors
class HelpFormatter extends CommonsHelpFormatter {
    protected String rtrim(String s) {
        return s
    }
}
