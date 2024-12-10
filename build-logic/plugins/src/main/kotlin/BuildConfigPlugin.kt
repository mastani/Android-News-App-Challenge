import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class BuildConfigPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        setProjectConfig(project)
    }

    private fun setProjectConfig(project: Project) {
        val libs: VersionCatalog = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        val versionName = libs.findVersion("version.name").get().requiredVersion
        val versionCode = libs.findVersion("version.code").get().requiredVersion.toInt()

        project.android().apply {

            flavorDimensions(
                "environment",
                "vendor"
            )

            productFlavors.create("develop").apply {
                dimension = "environment"
                buildConfigField("Integer", "VERSION_CODE", "$versionCode")
                buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
                buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
                buildConfigField("String", "API_KEY", "\"aab1d313c05c4f188318add012719d34\"")
                resValue("string", "app_name", "News App Test")
            }

            productFlavors.create("production").apply {
                dimension = "environment"
                buildConfigField("Integer", "VERSION_CODE", "$versionCode")
                buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
                buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
                buildConfigField("String", "API_KEY", "\"aab1d313c05c4f188318add012719d34\"")
                resValue("string", "app_name", "News App")
            }

            productFlavors.create("site").apply {
                dimension = "vendor"
                versionNameSuffix = "-site"
            }

            productFlavors.create("bazar").apply {
                dimension = "vendor"
                versionNameSuffix = "-b"
            }

            productFlavors.create("myket").apply {
                dimension = "vendor"
                versionNameSuffix = "-mk"
            }

            productFlavors.create("playstore").apply {
                dimension = "vendor"
                versionNameSuffix = "-ps"
            }
        }
    }

    private fun Project.android(): BaseExtension {
        return extensions.findByName("android") as? BaseExtension ?: error("Not an Android module: $name")
    }
}