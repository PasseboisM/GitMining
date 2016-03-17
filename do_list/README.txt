    本do_list目录下为小组的仿真todo/doing/done工作进度记录白版
    
    
 目的：
    跟踪项目中比commit层次更细的工作单元完成情况，以便计算工作进度，合理分配时间；
    展示工作完成情况，激发成员工作热情、自信心。

使用说明：
    本目录下每一个txt文件表示一个工作中的具体工作，文件所在的子目录位置表示其完成情况：
        to_do -> 尚未开始
        doing -> 本项工作已开始
        recent_done -> 工作已完成
        done_records -> 完成的工作归档（由项目负责人xjh14视情况几天或一周进行一次归档）
    小组内各成员在工作中，发现待办事项时，应在to_do目录中增加一个txt文件，其命名规则为"MM-DD-hh-mm_工作种类_简述.txt"；
        其中“工作种类”分为：
            Document,
            LogicDevelopment,  
            PresentationDevelopment,
            Refractoring,
            Debug,
            Test
            PerformanceImprove,
            Other.
        “简述”为对任务的简明描述，尽量控制在12字内。
        txt内写上任务描述（比文件名中的简述详细一些），任务发现时间（即标题的"MM-DD-hh-mm"）。
    当任务状态变化时，向文件内添加对应的时间戳，并将文件移动到新的目录中。


注意：
    每当新建/修改本目录内的文件后，应尽快使用git进行commit,push.
    
    
示例：
    xjh14编写了一个Comparator时，发现自己忘记了arg0,arg1的正确顺序，他决定先继续其他编码工作，在之后的测试中再检验正确性，于是他向to_do目录中添加：03-17-19-30_Test_测试User排序Comparator.txt，并在其中写上：
        工作说明：测试对User进行排序的Comparator能否正常工作，注意Comparator内的两个参数顺序可能有误。
        工作发现时间：03-17-19-30
    之后他开始此工作，向文件里添加：
        开始时间:03-17-20-00
    并将文件移至doing.
    ...之后工作完成时，他也如上写上“完成时间”，并再次挪移文件位置。