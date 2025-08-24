package com.itos.xplan.utils

import android.os.Build
import com.itos.xplan.BuildConfig
import com.kongzue.dialogx.dialogs.MessageDialog

/**
 * 设备工具类，提供设备相关的实用方法
 */
object DeviceUtils {
    
    /**
     * 获取当前设备的品牌名称（小写）
     * @return 设备品牌名称，如 xiaomi, samsung 等
     */
    fun getBrand(): String {
        return Build.BRAND.lowercase()
    }
    
    /**
     * 在调试模式下显示当前设备品牌信息
     */
    fun showBrandInfoIfDebug() {
        if (BuildConfig.DEBUG) {
            val brand = getBrand()
            val brand2 = getDeviceVariant()
            MessageDialog.build()
                .setTitle("品牌检测")
                .setMessage("当前设备品牌: $brand \n最终名称: $brand2")
                .setOkButton("好") { _, _ -> false }
                .show()
        }
    }
    
    /**
     * 获取设备的变体名称，用于加载特定品牌的配置
     * 将不同的品牌映射到对应的变体名称
     *
     * @return 变体名称，如 miui, vivo, color 等
     */
    fun getDeviceVariant(): String {
        val brand = getBrand()
        return when (brand) {
            "xiaomi", "redmi", "poco" -> "miui"
            "vivo", "iqoo" -> "vivo"
            "oneplus", "oppo" -> "color"
            "meizu" -> "meizu"
            "samsung" -> "samsung"
            else -> brand
        }
    }

    /**
     * 判断当前设备是否为MIUI系统
     * @return true表示是MIUI系统（小米、红米、POCO），false表示其他系统
     */
    fun isMiui(): Boolean {
        return getDeviceVariant() == "miui"
    }

    /**
     * 判断当前设备是否为MIUI或vivo系统
     * @return true表示是MIUI系统（小米、红米、POCO）或vivo系统（vivo、iQOO），false表示其他系统
     */
    fun isMiuiOrVivo(): Boolean {
        val variant = getDeviceVariant()
        return variant == "miui" || variant == "vivo"
    }
}
