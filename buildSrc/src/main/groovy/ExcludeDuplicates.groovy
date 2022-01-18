import com.github.jengelman.gradle.plugins.shadow.transformers.Transformer
import com.github.jengelman.gradle.plugins.shadow.transformers.TransformerContext
import org.gradle.api.tasks.Internal
import shadow.org.apache.tools.zip.ZipOutputStream
import org.gradle.api.file.FileTreeElement

class ExcludeDuplicates implements Transformer {

    @Internal
    Map<String, Boolean> map = new HashMap<>()

    boolean canTransformResource(FileTreeElement element) {

        def path = element.relativePath.pathString


        boolean visited = map.getOrDefault(path, false)
        if (!visited) {
            map.put(path, true)
        }

        println ("canTransformResource "+ path + " " + visited)
        return visited


    }

    void transform(TransformerContext context) {
        println("transform " + context.path)
    }

    boolean hasTransformedResource() { true }

    void modifyOutputStream(ZipOutputStream jos,boolean preserveFileTimestamps) {}
}
