package xyz.weizj.manger.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import xyz.weizj.manger.mapper.CategoryMapper;
import xyz.weizj.model.vo.product.CategoryExcelVo;

import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private final static Integer BATCH_COUNT = 100;

    private List cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        CategoryExcelVo data = (CategoryExcelVo) o;
        cacheDataList.add(data);
        if (cacheDataList.size()>=BATCH_COUNT){
            saveData();
            cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    public void saveData(){
        categoryMapper.batchInsert(cacheDataList);
    }
}
