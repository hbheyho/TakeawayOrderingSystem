package com.project.sell.utils;

import com.project.sell.vo.ResultVo;

/**
 * @Author: HB
 * @Description: 前端展示工具类
 * @CreateDate: 16:48 2020/10/24
 */

public class ResultVoUtil {

    /**
     * @Author: HB
     * @Description: 操作成功 - 附带数据
     * @Date: 16:49 2020/10/24
     * @Params: null
     * @Returns:
    */
    public static ResultVo success(Object object) {

        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;

    }

    /**
     * @Author: HB
     * @Description: 操作成功 - 不附带数据
     * @Date: 16:49 2020/10/24
     * @Params: null
     * @Returns:
     */
    public static ResultVo success() {

        return success(null);

    }

    /**
     * @Author: HB
     * @Description: 操作失败
     * @Date: 16:49 2020/10/24
     * @Params: null
     * @Returns:
     */
    public static ResultVo error(Integer code, String msg) {

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;

    }



}
