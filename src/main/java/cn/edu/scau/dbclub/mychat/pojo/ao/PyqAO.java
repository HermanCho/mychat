package cn.edu.scau.dbclub.mychat.pojo.ao;

/**
 * @author 杜科
 * @description 朋友圈视图表单
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
public class PyqAO {

    private Integer sharerId;

    private String content;

    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PyqAO{" +
                "sharerId=" + sharerId +
                ", content='" + content + '\'' +
                '}';
    }
}
