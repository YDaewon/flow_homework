<template>
  <div class="p-6 max-w-2xl mx-auto">
    <h2 class="text-xl font-bold mb-4">📂 파일 확장자 차단</h2>

    <!-- 고정 확장자 -->
    <div class="mb-6">
      <p class="font-medium mb-2">고정 확장자</p>
      <div class="flex flex-wrap gap-4">
        <label
          v-for="ext in fixedExtensions"
          :key="ext.name"
          class="flex items-center gap-2"
        >
          <input
            type="checkbox"
            :checked="ext.active"
            @change="toggleExtension(ext.name, !ext.active)"
          />
          {{ ext.name }}
        </label>
      </div>
    </div>

    <!-- 커스텀 확장자 -->
    <div>
      <p class="font-medium mb-2">커스텀 확장자</p>
      <div class="flex gap-2 mb-3">
        <input
          v-model="newExt"
          placeholder="확장자 입력 (예: png)"
          class="border rounded p-2 flex-1"
          @input="onInput"
          @keyup.enter="createExtension"
        />
        <button
          @click="createExtension"
          class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
        >
          추가
        </button>
      </div>

      <div class="border rounded p-3 min-h-[100px]">
        <div class="flex flex-wrap gap-2">
          <span
            v-for="ext in customExtensions"
            :key="ext.name"
            class="bg-gray-200 px-3 py-1 rounded-full text-sm flex items-center gap-2"
          >
            {{ ext.name }}
            <button
              @click="deleteExtension(ext.name)"
              class="text-red-500 font-bold"
            >
              ×
            </button>
          </span>
        </div>
        <p class="text-xs text-gray-500 mt-2">
          {{ customExtensions.length }}/200
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "./api";

const API_BASE = "/api/extension";

const extensions = ref([]);
const newExt = ref("");

// 입력 제한 (알파벳, 숫자, 최대 20자)
const onInput = (e) => {
  // 소문자 알파벳만 허용
  let value = e.target.value.replace(/[^a-z]/g, "");

  // 길이 제한 20자
  if (value.length > 20) {
    value = value.substring(0, 20);
  }

  newExt.value = value;
};

// 데이터 분리 (computed로 필터링)
const fixedExtensions = computed(() =>
  extensions.value.filter((ext) => ext.type === "FIXED")
);
const customExtensions = computed(() =>
  extensions.value.filter((ext) => ext.type === "CUSTOM")
);

// 전체 목록 불러오기
const fetchExtensions = async () => {
  try {
    const res = await axios.get("/api/extensions");
    extensions.value = res.data.result;
  } catch (err) {
    console.error(err);
    alert("확장자 목록 불러오기 실패");
  }
};

// 확장자 토글
const toggleExtension = async (name, active) => {
  try {
    await axios.patch(API_BASE, { name, active });
    fetchExtensions();
  } catch (err) {
    alert(err.response?.data?.message || "토글 실패");
  }
};

// 커스텀 확장자 추가
const createExtension = async () => {
  const val = newExt.value.trim().toLowerCase();
  if (!val) return;
  try {
    await axios.post(API_BASE, { name: val });
    newExt.value = "";
    fetchExtensions();
  } catch (err) {
    alert(err.response?.data?.message || "추가 실패");
  }
};

// 커스텀 확장자 삭제
const deleteExtension = async (name) => {
  if (!confirm(`${name} 확장자를 삭제할까요?`)) return;
  try {
    await axios.delete(API_BASE, { data: { name } });
    fetchExtensions();
  } catch (err) {
    alert(err.response?.data?.message || "삭제 실패");
  }
};

onMounted(fetchExtensions);
</script>