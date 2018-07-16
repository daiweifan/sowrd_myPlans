<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="scheduleJobInfoForm">
		
			<input type="hidden" id="id" name="id" value="">
			<div class="row">
					<div class="ibox-content">
						<h2>一些简单例子</h2>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>表达式</th>
                                    <th>意义</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>"0 0 12 * * ?"</td>
                                    <td>每天中午12点触发</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>"0 15 10 ? * *"</td>
                                    <td>每天上午10:15触发</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>"0 15 10 * * ? 2005"</td>
                                    <td>2005年的每天上午10:15触发</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>"0 * 14 * * ?"</td>
                                    <td>在每天下午2点到下午2:59期间的每1分钟触发</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>"0 0/5 14 * * ?"</td>
                                    <td>在每天下午2点到下午2:55期间的每5分钟触发</td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>"0 0/5 14,18 * * ?"</td>
                                    <td>在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发</td>
                                </tr>
                                <tr>
                                    <td>7</td>
                                    <td>"0 10,44 14 ? 3 WED"</td>
                                    <td>每年三月的星期三的下午2:10和2:44触发</td>
                                </tr>
                                <tr>
                                	<td>8</td>
                                    <td>"0 15 10 ? * MON-FRI"</td>
                                    <td>周一至周五的上午10:15触发</td>
                                </tr>
                                <tr>
                                	<td>9</td>
                                    <td>"0 15 10 15 * ?"</td>
                                    <td>每月15日上午10:15触发</td>
                                </tr>
                                <tr>
                                	<td>10</td>
                                    <td>"0 15 10 L * ?"</td>
                                    <td>每月最后一日的上午10:15触发</td>
                                </tr>
                                <tr>
                                	<td>11</td>
                                    <td>"0 15 10 ? * 6L"</td>
                                    <td>每月的最后一个星期五上午10:15触发</td>
                                </tr>
                                <tr>
                                	<td>12</td>
                                    <td>"0 15 10 ? * 6L 2002-2005"</td>
                                    <td>2002年至2005年的每月的最后一个星期五上午10:15触发</td>
                                </tr>
                                <tr>
                                	<td>13</td>
                                    <td>"0 15 10 ? * 6#3"</td>
                                    <td>每月的第三个星期五上午10:15触发</td>
                                </tr>
                            </tbody>
                        </table>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="beanName" labelName="Bean名称" underline="true" required="true" />
					<@u.input id="params" labelName="参数" underline="true"  required="true" />
					<@u.input id="note" labelName="备注" />
				</div>
				<div class="col-sm-6 b-r">
					<@u.input id="methodName" labelName="方法名称" underline="true"  required="true" />
					<@u.input id="cronExpression" labelName="cron表达式" type="email" underline="true"  required="true" />
				</div>
			</div>


			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="ScheduleJobInfoDlg.addSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="ScheduleJobInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/scheduleJob/add.js"></script>

</#assign>
<#include "/backend/shared/container.ftl" />  
