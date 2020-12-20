package app.andika.dindinassignment.utilities

import android.animation.Animator
import android.widget.TextView

object TextAnimation {

    fun setTextWithSmoothAnimation(textView: TextView, textStart: String, textEnd : String) {
        textView.animate().setDuration(300).setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                textView.text = textStart
            }

            override fun onAnimationEnd(animation: Animator?) {
                textView.text = textEnd
//                textView.animate().setListener(null).setDuration(300).alpha(1f)
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        }).alpha(1f)
    }
}