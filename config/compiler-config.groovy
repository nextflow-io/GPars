import org.codehaus.groovy.control.customizers.ImportCustomizer

// Create an ImportCustomizer and add the desired imports
def importCustomizer = new ImportCustomizer()
importCustomizer.addImports('groovy.test.GroovyTestCase')

// Add the ImportCustomizer to the configuration
configuration.addCompilationCustomizers(importCustomizer)
