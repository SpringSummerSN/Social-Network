$target = [System.EnvironmentVariableTarget]::User

[System.Environment]::SetEnvironmentVariable('SpringSummerUsername', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerPassword', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerJWT', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerMailUsername', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerMailPassword', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerAdminEmail', $null, $target)
[System.Environment]::SetEnvironmentVariable('SpringSummerAdminPassword', $null, $target)
