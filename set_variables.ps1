$target = [System.EnvironmentVariableTarget]::User
[System.Environment]::SetEnvironmentVariable('SpringSummerUsername', 'root', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerPassword', 'root', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerJWT', '404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerMailUsername', 'frameworkibiznesowe@gmail.com', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerMailPassword', 'dnfwugbkhqcceafj', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerAdminEmail', 'admin@gmail.com', $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerAdminPassword', 'Admin123#', $target)