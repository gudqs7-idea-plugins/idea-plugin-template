package cn.gudqs7.plugins.common.action;

import cn.gudqs7.plugins.common.util.jetbrain.ExceptionUtil;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author wq
 * @date 2022/6/3
 */
public abstract class AbstractAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent e) {
        try {
            onCheck(e);
        } catch (Throwable ex) {
            ExceptionUtil.handleException(ex);
        } finally {
            destroy(e);
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            onClick(e);
        } catch (Throwable ex) {
            ExceptionUtil.handleException(ex);
        } finally {
            destroy(e);
        }
    }

    /**
     * 销毁资源用的
     * @param e action上下文
     */
    protected void destroy(AnActionEvent e) {

    }

    /**
     * 显示前调用, 判断是否显示
     *
     * @param e 事件源对象
     */
    protected abstract void onCheck(@NotNull AnActionEvent e);

    /**
     * 动作触发时的逻辑代码
     *
     * @param e 事件源对象
     */
    protected abstract void onClick(@NotNull AnActionEvent e);

    protected void notShow(@NotNull AnActionEvent e) {
        e.getPresentation().setVisible(false);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

}
