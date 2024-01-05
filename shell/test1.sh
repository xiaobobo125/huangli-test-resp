#!/bin/bash
# 检查参数数量
if [ $# -ne 3 ]; then
    echo "程序需要输入三个参数，分别为 ：<文件> <要查找的字符串> <输出的文件>"
    exit 1
fi

# 获取参数
file=$1
search_string=$2
output_file=$3

# 在文件中搜索指定内容，并将结果保存到新文件
grep -n "$search_string" "$file" > "$output_file"

# 将结果输出到控制台
cat "$output_file"
