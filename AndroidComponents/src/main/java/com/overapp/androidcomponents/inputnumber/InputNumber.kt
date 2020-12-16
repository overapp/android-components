package com.overapp.androidcomponents.inputnumber

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.overapp.androidcomponents.R

/**
 * InputNumber.kt
 * InputNumberDemo
 *
 * Created by Giampietro Fronteddu on 12/12/20.
 * Copyright © 2020 OverApp. All rights reserved.
 */
class InputNumber : ConstraintLayout {

    private lateinit var decrementButton: MaterialButton
    private lateinit var incrementButton: MaterialButton
    private lateinit var editText: TextInputEditText

    private var totalAmount = defaultStartAmount
    private var minValue = defaultMinValue
    private var maxValue = defaultMaxValue

    //region Constructors

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    //endregion

    //region Private Methods

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Inflate
        inflate(context, R.layout.layout_overapp_input_number, this)

        // Load attributes
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.InputNumber, defStyle, 0
        )
        totalAmount =
            typedArray.getResourceId(R.styleable.InputNumber_startAmount, defaultStartAmount)
        minValue = typedArray.getResourceId(R.styleable.InputNumber_minValue, defaultMinValue)
        maxValue = typedArray.getResourceId(R.styleable.InputNumber_maxValue, defaultMaxValue)

        // Init Layout
        initLayoutElement()
    }

    private fun initLayoutElement() {
        // inflate elements

        // Set Decrement Button
        decrementButton = findViewById(R.id.overappInputNumberDecrementImageButton)

        // Set Increment Button
        incrementButton = findViewById(R.id.overappInputNumberIncrementImageButton)

        // Set Button Enable
        setButtonEnable()

        // Set Edit Text
        editText = findViewById(R.id.overappInputNumberEditText)
        setEditTextAmount()

        // Se on click for increment
        incrementButton.setOnClickListener {
            onIncrement()
        }

        // Se on click for decrement
        decrementButton.setOnClickListener {
            onDecrement()
        }
    }

    private fun setEditTextAmount() {
        editText.setText(totalAmount.toString())
    }

    private fun onIncrement() {
        if (totalAmount < maxValue) {
            totalAmount++
            setEditTextAmount()
            setButtonEnable()
        }
    }

    private fun onDecrement() {
        if (totalAmount > minValue) {
            totalAmount--
            setEditTextAmount()
            setButtonEnable()
        }
    }

    private fun setButtonEnable() {
        decrementButton.isEnabled = totalAmount > minValue
        incrementButton.isEnabled = totalAmount < maxValue

    }
    //endregion

    companion object {
        private const val defaultStartAmount = 0
        private const val defaultMinValue = 0
        private const val defaultMaxValue = 100
    }
}