package cn.gudqs7.plugins.common.action.intention;

import cn.gudqs7.plugins.common.util.jetbrain.ExceptionUtil;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * @author wenquan
 * @date 2021/9/30
 */
public abstract class AbstractEditorIntentionAction extends PsiElementBaseIntentionAction {

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
        try {
            return onCheck(project, editor, element);
        } catch (Throwable ex) {
            ExceptionUtil.handleException(ex);
            return false;
        }
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
        try {
            PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
            Document document = editor.getDocument();
            onClick(project, editor, element, document, psiDocumentManager);
        } catch (Throwable ex) {
            ExceptionUtil.handleException(ex);
        } finally {
            destroy(project, editor, element);
        }
    }

    /**
     * 判断此提示是否显示
     *
     * @param project 项目
     * @param editor  编辑器
     * @param element 当前元素
     * @return 此提示是否显示
     */
    protected abstract boolean onCheck(@NotNull Project project, Editor editor, @NotNull PsiElement element);

    /**
     * 执行动作
     *
     * @param project            项目
     * @param editor             编辑器
     * @param element            当前元素
     * @param elementDocument    当前元素所在文件的文档对象(可编辑)
     * @param psiDocumentManager 文档管理器对象
     */
    protected abstract void onClick(Project project, Editor editor, PsiElement element, Document elementDocument, PsiDocumentManager psiDocumentManager);

    protected void destroy(Project project, Editor editor, PsiElement element) {

    }

}
