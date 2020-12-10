project 'Default', {
    release 'My DSL Test', {
        pipeline 'My DSL Test Pipeline', {
            stage 'First stage', {
                task 'First task', {
                    taskType = 'COMMAND'
                }
            }
        }
    }
}