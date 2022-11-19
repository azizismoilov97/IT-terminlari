package admiral.group.itterminlari.presentation.common

import org.jetbrains.annotations.NonNls
import timber.log.Timber

object TerminLog {

    fun logInfo(@NonNls message: String?){
        Timber.i(message)
    }

    fun logInfo(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.i(t, message, args)
    }

    fun logInfo(@NonNls message: String?, vararg args: Any?){
        Timber.i(message, args)
    }

    fun logWarning(@NonNls message: String?){
        Timber.w(message)
    }

    fun logWarning(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.w(t, message, args)
    }

    fun logWarning(@NonNls message: String?, vararg args: Any?){
        Timber.w(message, args)
    }

    fun logError(@NonNls message: String?){
        Timber.e(message)
    }

    fun logError(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.e(t, message, args)
    }

    fun logError(@NonNls message: String?, vararg args: Any?){
        Timber.wtf(message, args)
    }

    fun logAssert(@NonNls message: String?){
        Timber.wtf(message)
    }

    fun logAssert(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.wtf(t, message, args)
    }

    fun logAssert(@NonNls message: String?, vararg args: Any?){
        Timber.wtf(message, args)
    }

    fun logVerbose(@NonNls message: String?){
        Timber.v(message)
    }

    fun logVerbose(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.v(t, message, args)
    }

    fun logVerbose(@NonNls message: String?, vararg args: Any?){
        Timber.v(message, args)
    }

    fun logDebug(@NonNls message: String?){
        Timber.d(message)
    }

    fun logDebug(t: Throwable?, @NonNls message: String?, vararg args: Any?){
        Timber.d(t, message, args)
    }

    fun logDebug(@NonNls message: String?, vararg args: Any?){
        Timber.d(message, args)
    }

}