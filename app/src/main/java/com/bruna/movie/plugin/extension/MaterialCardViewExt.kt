package com.bruna.movie.plugin.extension

import android.annotation.SuppressLint
import android.content.Context
import com.bruna.movie.R
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

const val FAB_MARGIN = 0F
const val ROUNDED_CORNER_RADIUS = 0F
const val CRADLE_VERTICAL_OFFSET = 0F
const val FAB_DIAMETER = 75F

fun MaterialCardView?.createTicketViewSmall() {
    this?.background = getMaterialShapeDrawable(this?.context, getShapeAppearanceModelByTicketSmall())
}

fun MaterialCardView?.createTicketViewLarge() {
    this?.background = getMaterialShapeDrawable(this?.context, getShapeAppearanceModelByTicketLarge())
}

private fun getMaterialShapeDrawable(
    context: Context?,
    shapeAppearanceModel: ShapeAppearanceModel
): MaterialShapeDrawable {
    return MaterialShapeDrawable(shapeAppearanceModel).apply {
        context.getColorCompat(R.color.colorPrimaryLight)?.let(this::setTint)
    }
}

private fun getShapeAppearanceModelByTicketSmall(): ShapeAppearanceModel {
    return ShapeAppearanceModel.Builder()
        .setRightEdge(getTicketEdgeTreatment())
        .build()
}

private fun getShapeAppearanceModelByTicketLarge(): ShapeAppearanceModel {
    return ShapeAppearanceModel.Builder()
        .setRightEdge(getTicketEdgeTreatment())
        .setLeftEdge(getTicketEdgeTreatment())
        .build()
}

@SuppressLint("RestrictedApi")
private fun getTicketEdgeTreatment(): BottomAppBarTopEdgeTreatment {
    return BottomAppBarTopEdgeTreatment(FAB_MARGIN, ROUNDED_CORNER_RADIUS, CRADLE_VERTICAL_OFFSET).apply {
        fabDiameter = FAB_DIAMETER
    }
}
