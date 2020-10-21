package th.co.cdgs.mobile.textinputshadowlib

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.card.MaterialCardView

class TextInputShadow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    init {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        val text: CharSequence?
        val hint: CharSequence?
        val imeOptions: Int
        val inputType: Int
        val nextFocusDownId: Int
        val nextFocusForwardId: Int
        val nextFocusLeftId: Int
        val nextFocusRightId: Int
        val nextFocusUpId: Int
        val maxLength: Int
        val drawable: Int
        if (attrs == null) {
            text = null
            hint = null
            imeOptions = View.NO_ID
            inputType = View.NO_ID
            nextFocusDownId = View.NO_ID
            nextFocusForwardId = View.NO_ID
            nextFocusLeftId = View.NO_ID
            nextFocusRightId = View.NO_ID
            nextFocusUpId = View.NO_ID
            maxLength = View.NO_ID
            drawable = View.NO_ID
        } else {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputShadow)
            text = typedArray.getText(R.styleable.TextInputShadow_android_text)
            hint = typedArray.getText(R.styleable.TextInputShadow_android_hint)
            imeOptions = typedArray.getInt(R.styleable.TextInputShadow_android_imeOptions, View.NO_ID)
            inputType = typedArray.getInt(
                    R.styleable.TextInputShadow_android_inputType,
                    InputType.TYPE_CLASS_TEXT
            )
            nextFocusDownId = typedArray.getResourceId(
                    R.styleable.TextInputShadow_android_nextFocusDown,
                    View.NO_ID
            )
            nextFocusForwardId = typedArray.getResourceId(
                    R.styleable.TextInputShadow_android_nextFocusForward,
                    View.NO_ID
            )
            nextFocusLeftId = typedArray.getResourceId(
                    R.styleable.TextInputShadow_android_nextFocusLeft,
                    View.NO_ID
            )
            nextFocusRightId = typedArray.getResourceId(
                R.styleable.TextInputShadow_android_nextFocusRight,
                View.NO_ID
            )
            nextFocusUpId =
                typedArray.getResourceId(
                    R.styleable.TextInputShadow_android_nextFocusUp,
                    View.NO_ID
                )
            maxLength = typedArray.getInt(R.styleable.TextInputShadow_android_maxLength, -1)
            drawable =
                typedArray.getResourceId(R.styleable.TextInputShadow_android_drawable, View.NO_ID)
            typedArray.recycle()
        }
        View.inflate(context, R.layout.text_input_shadow, this)
        val card = findViewById<MaterialCardView>(R.id.card_input_shadow)
        val edt = findViewById<AppCompatEditText>(R.id.text_input_shadow)
        val img = findViewById<AppCompatImageView>(R.id.img_input_shadow)
        if (drawable != View.NO_ID) {
            img.setImageResource(drawable)
        }
        edt.hint = hint
        edt.setText(text)
        if (maxLength != View.NO_ID) {
            edt.setMaxLength(maxLength)
        }
        if (imeOptions != View.NO_ID) {
            edt.imeOptions = imeOptions
        }
        if (inputType != View.NO_ID) {
            edt.inputType = inputType
        }
        edt.nextFocusDownId = nextFocusDownId
        edt.nextFocusForwardId = nextFocusForwardId
        edt.nextFocusLeftId = nextFocusLeftId
        edt.nextFocusRightId = nextFocusRightId
        edt.nextFocusUpId = nextFocusUpId
        edt.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                card.cardElevation = 6f.toDip()
            } else {
                card.cardElevation = 0f.toDip()
            }
        }
    }

    private fun EditText?.setMaxLength(length: Int) {
        val filterArray = arrayOf(InputFilter.LengthFilter(length))
        this?.filters = filterArray
    }

    private fun Float?.toDip() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this ?: -1f, resources.displayMetrics)
}