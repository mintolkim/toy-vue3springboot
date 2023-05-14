<template>
    <div class="mb-3 accordion" id="accordionExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                   글 목록 보기
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    요기다가 list
                </div>
            </div>
        </div>
    </div>
    <div class="mb-3 card">
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <h5 class="mb-3 card-title text-start">제목</h5>
                    <h6 class="mb-4 card-subtitle text-muted text-start">
                        게시 날짜
                    </h6>
                </li>
                <li class="p-4 list-group-item">
                    콘텐츠
                </li>
                <li class="list-group-item">
                    페이지 네이션
                </li>
            </ul>
        </div>
    </div>
    <div class="text-end">
        <button type="button" class="btn btn-outline-primary">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
            </svg>
            글 쓰기</button>
    </div>
</template>

<script>
</script>

<style>

</style>