package com.example.hhtestus.validation

import androidx.compose.ui.focus.FocusRequester

fun handleTextInput(
    input: String,
    index: Int,
    code: String,
    focusRequesters: List<FocusRequester>,
    updateCode: (String) -> Unit
) {
    if (input.length <= 1) {
        val newCode = buildString {
            append(code.substring(0, index))
            append(input)
            if (index < code.length - 1) append(code.substring(index + 1))
        }
        updateCode(newCode)

        if (input.isNotEmpty() && index < 3) {
            focusRequesters[index + 1].requestFocus()
        } else if (input.isEmpty() && index > 0) {
            focusRequesters[index - 1].requestFocus()
        }
    }
}