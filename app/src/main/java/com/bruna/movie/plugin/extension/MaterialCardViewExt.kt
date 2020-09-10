package com.bruna.movie.plugin.extension

import android.content.Context
import com.bruna.movie.R
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

fun MaterialCardView?.createTicketViewSmall() {
    this?.background = getMaterialShapeDrawable(this?.context, getShapeAppearanceModelByTicketSmall())
}

fun MaterialCardView?.createTicketViewLarge() {
    this?.background = getMaterialShapeDrawable(this?.context, getShapeAppearanceModelByTicketLarge())
}

private fun getMaterialShapeDrawable(context: Context?, shapeAppearanceModel: ShapeAppearanceModel): MaterialShapeDrawable {
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

private fun getTicketEdgeTreatment(): BottomAppBarTopEdgeTreatment {
    return BottomAppBarTopEdgeTreatment(0F, 0F, 0F).apply {
        fabDiameter = 75F
    }
}
