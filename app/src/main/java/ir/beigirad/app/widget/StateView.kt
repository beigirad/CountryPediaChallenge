package ir.beigirad.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import ir.beigirad.app.R
import kotlinx.android.synthetic.main.include_state_view.view.*

class StateView : RelativeLayout {

    private var mainView: View? = null
    private var stateView: View? = null


    private lateinit var errorImg: ImageView
    private lateinit var errorTv: TextView
    private lateinit var errorBtn: Button

    private lateinit var emptyImg: ImageView
    private lateinit var emptyTv: TextView

    private lateinit var loadingPr: ProgressBar


    private var _errorText: String? = null
    private var _errorImage: Int? = null

    private var _emptyText: String? = null
    private var _emptyImage: Int? = null

    private var state: State = State.LOADING

    private var isMinimum = false

    private var retryListener: RetryListener? = null


    constructor(context: Context) : super(context) {
        initViews()
        setValues()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initViews()
        setValues()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initViews()
        setValues()
    }


    private fun initViews() {
        val stateView =
            LayoutInflater.from(context).inflate(R.layout.include_state_view, this, false)
        addView(stateView, 0)

        errorImg = state_img_error
        errorTv = state_message_error
        errorBtn = state_btn

        emptyImg = state_img_blank
        emptyTv = state_message_blank

        loadingPr = state_progress

        errorBtn.setOnClickListener {
            retryListener?.retry()
        }
    }

    fun setValues(
        _errorText: String = context.getString(R.string.an_error_occured),
        _errorImage: Int = R.mipmap.error_placeholder,

        _emptyText: String = context.getString(R.string.no_showing_data),
        _emptyImage: Int = R.mipmap.empty_placeholder
    ) {
        this._emptyImage = _emptyImage
        this._emptyText = _emptyText

        this._errorImage = _errorImage
        this._errorText = _errorText

        bindValues()
        refreshState()
    }

    fun setMinimumSize(isMinimum: Boolean) {
        this.isMinimum = isMinimum
    }

    private fun bindValues() {
        errorTv.text = _errorText
        _errorImage?.let { errorImg.setImageResource(it) }

        emptyTv.text = _emptyText
        _emptyImage?.let { emptyImg.setImageResource(it) }
    }

    fun setState(state: State) {
        this.state = state
        refreshState()
    }

    private fun refreshState() {

        mainView?.visibility = if (state == State.SUCCESS) View.VISIBLE else View.GONE
        stateView?.visibility = if (state != State.SUCCESS) View.VISIBLE else View.GONE

        errorImg.visibility = if (state == State.ERROR && !isMinimum) View.VISIBLE else View.GONE
        errorTv.visibility = if (state == State.ERROR) View.VISIBLE else View.GONE
        errorBtn.visibility = if (state == State.ERROR) View.VISIBLE else View.GONE

        emptyImg.visibility = if (state == State.BLANK && !isMinimum) View.VISIBLE else View.GONE
        emptyTv.visibility = if (state == State.BLANK) View.VISIBLE else View.GONE

        loadingPr.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
    }

    fun setRetryListener(retryListener: RetryListener) {
        this.retryListener = retryListener
    }

    fun setRetryListener(retryListener: () -> Unit) {
        this.retryListener = object : RetryListener {
            override fun retry() {
                retryListener()
            }
        }
    }

    interface RetryListener {
        fun retry()
    }

    enum class State {
        LOADING, ERROR, SUCCESS, BLANK
    }
}
