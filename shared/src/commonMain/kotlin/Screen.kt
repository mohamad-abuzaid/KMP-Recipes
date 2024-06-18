import model.Recipe

/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
sealed interface Screens {
    data object RecipesList : Screens
    data class RecipeDetails(
        val recipe: Recipe
    ) : Screens
}