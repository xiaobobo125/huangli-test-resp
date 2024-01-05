<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.empName"
        placeholder="姓名"
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />
      <el-select
        v-model="listQuery.deptName"
        placeholder="部门名称"
        clearable
        style="width: 90px"
        class="filter-item"
      >
        <el-option v-for="item in deptNameOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <el-select
        v-model="listQuery.empDegreeName"
        placeholder="学历"
        clearable
        style="width: 90px"
        class="filter-item"
      >
        <el-option v-for="item in empDegreeNameOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="dataList"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="序号" type="index" width="50" align="center":index="indexMethod" />
      <el-table-column label="职工姓名" prop="empName" />
      <el-table-column label="性别" align="center">
        <template slot-scope="{row}">
          <span>{{ row.sex === '1' ? '男' : '女' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center">
        <template slot-scope="{row}">
          <span>{{ row.age }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门名称" align="center">
        <template slot-scope="{row}">
          <span>{{ getDicText(row.deptName, deptNameOptions) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学历" align="center">
        <template slot-scope="{row}">
          <span>{{ getDicText(row.empDegreeName, empDegreeNameOptions) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleCreate(row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="page.page" :limit.sync="page.limit" @pagination="getList" />

    <el-dialog :title="dialogStatus" :visible.sync="dialogFormVisible">
      <el-form ref="editForm" :rules="rules" :model="form" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名">
          <el-input v-model="form.empName" placeholder="姓名" style="width: 200px;" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio :label="'1'">男</el-radio>
            <el-radio :label="'2'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="form.age" :min="1" :max="200" label="年龄" />
        </el-form-item>
        <el-form-item label="部门名称">
          <el-select v-model="form.deptName" placeholder="所属部门" clearable>
            <el-option v-for="item in deptNameOptions" :key="item.key" :label="item.label" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="学历">
          <el-select v-model="form.empDegreeName" placeholder="学历" clearable>
            <el-option v-for="item in empDegreeNameOptions" :key="item.key" :label="item.label" :value="item.key" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="doSave()">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list, edit, deleteData } from '@/api/emp'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data() {
    return {
      /** 部门下拉列表 */
      deptNameOptions: [{ key: '1', label: '业务部' }, { key: '2', label: '后勤部' }, { key: '3', label: '人事部' }],
      /** 学历下拉列表 */
      empDegreeNameOptions: [{ key: '1', label: '大专' }, { key: '2', label: '本科' }, { key: '3', label: '研究生' }],
      /** 查询条件 */
      listQuery: {
        empName: '',
        deptName: '',
        empDegreeName: ''
      },
      /** 分页条件 */
      page: {
        page: 1,
        limit: 10
      },
      /** 数据列表 */
      dataList: [],
      /** 总条数 */
      total: 0,
      /** 列表懒加载标志 */
      listLoading: false,
      /** 列表导出懒加载标志 */
      downloadLoading: false,
      /** 添加编辑列表弹窗控制开关 */
      dialogFormVisible: false,
      /** 添加编辑状态 */
      dialogStatus: '',
      /** 保存参数 */
      form: {
        sex: '1',
        age: 0,
        empName: '',
        deptName: '1',
        empDegreeName: '1'
      },
      /** 数据格式校验 */
      rules: {
        empName: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
        sex: [{ required: true, message: '性别不能为空', trigger: 'change' }],
        age: [{ required: true, message: '年龄不能为空', trigger: 'change' }],
        deptName: [{ required: true, message: '部门不能为空', trigger: 'change' }],
        empDegreeName: [{ required: true, message: '学历不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /**
     * 设置序号
     * @param index
     */
    indexMethod(index) {
      return (this.page.page - 1) * this.page.limit + index + 1
    },
    /**
     * 执行数据过滤
     */
    handleFilter() {
      this.getList()
    },
    /**
     * 添加修改数据
     */
    handleCreate(item) {
      this.dialogFormVisible = true
      if (item && item.empName) {
        this.form = item
      }
    },
    /**
     * 添加数据
     */
    doSave() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          edit(this.form).then(res => {
            this.$message.success('保存成功！')
            this.dialogFormVisible = false
            this.getList()
          })
        }
      })
    },
    /**
     * 删除数据
     */
    handleDelete(item) {
      deleteData(item.id).then(res => {
        this.$message.success('删除成功！')
        this.getList()
      })
    },
    /**
     * 导出数据
     */
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['职工姓名', '性别', '年龄', '部门名称', '学历']
        const filterVal = ['empName', 'sex', 'age', 'deptName', 'empDegreeName']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: '职员列表'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.dataList.map(v => filterVal.map(j => {
        if (j === 'sex') {
          return v[j] === '1' ? '男' : '女'
        } else if (j === 'deptName') {
          return this.getDicText(v[j], this.deptNameOptions)
        } else if (j === 'empDegreeName') {
          return this.getDicText(v[j], this.empDegreeNameOptions)
        } else {
          return v[j]
        }
      }))
    },
    /**
     * 获取数据列表
     */
    getList() {
      list(this.listQuery, this.page).then(res => {
        this.dataList = res.data
        this.total = res.total
      })
    },
    /**
     * 获取数据
     */
    getDicText(data, dicOptions) {
      return dicOptions.find(item => item.key === data)?.label
    }
  }
}
</script>

<style lang = "scss" scoped>
</style>
