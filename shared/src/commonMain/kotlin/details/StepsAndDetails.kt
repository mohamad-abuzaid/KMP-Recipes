package details

import AnimateInEffect
import CrossFadeTransitionSpec
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.Recipe
import sharedelementtransaction.SharedElement


/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
internal fun LazyListScope.StepsAndDetails(
    recipe: Recipe
) {
    item {
        SharedElement(
            key = "recipe-title-${recipe.id}",
            screenKey = "DetailsScreen",
            transitionSpec = CrossFadeTransitionSpec,
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }

        SharedElement(
            key = "recipe-description-${recipe.id}",
            screenKey = "DetailsScreen",
            transitionSpec = CrossFadeTransitionSpec
        ) {

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }

        AnimateInEffect(
            recipe = recipe,
            intervalStart = 0 / (recipe.instructions.size + recipe.ingredients.size + 2).toFloat(),
            content = {
                Text(
                    text = "INGREDIENTS",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
            })
    }

    itemsIndexed(recipe.ingredients) { index, value ->
        AnimateInEffect(
            intervalStart = (index + 1) / (recipe.instructions.size + recipe.ingredients.size + 1).toFloat(),
            recipe = recipe,
            content = {
                IngredientItem(recipe, value)
            }
        )
    }

    item {
        AnimateInEffect(
            recipe = recipe,
            intervalStart = (recipe.ingredients.size + 1) / (recipe.instructions.size + recipe.ingredients.size + 2).toFloat(),
            content = {
                Text(
                    text = "STEPS",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
            }
        )
    }

    itemsIndexed(recipe.instructions) { index, item ->
        AnimateInEffect(
            recipe = recipe,
            intervalStart = (recipe.ingredients.size + index + 1) / (recipe.instructions.size + recipe.ingredients.size + 1).toFloat(),
            content = {
                InstructionItem(recipe, index)
            })
    }
}