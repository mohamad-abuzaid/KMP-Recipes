package details

import RecipeDetailsLarge
import RecipeDetailsSmall
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import model.Recipe
import sensor.SensorManager


/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun RecipeDetails(
    recipe: Recipe,
    goBack: () -> Unit,
    sensorManager: SensorManager,
    isLarge: Boolean
) {
    if (isLarge)
        RecipeDetailsLarge(
            recipe = recipe,
            goBack = goBack,
            sensorManager = sensorManager
        )
    else
        RecipeDetailsSmall(
            recipe = recipe,
            goBack = goBack,
            sensorManager = sensorManager
        )
}