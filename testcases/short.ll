; ModuleID = 'short.c'
source_filename = "short.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i16, align 2
  %3 = alloca i16, align 2
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 2, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 2, ptr %3) #2
  store i16 1, ptr %2, align 2, !tbaa !3
  store i16 2, ptr %3, align 2, !tbaa !3
  %4 = load i16, ptr %2, align 2, !tbaa !3
  store i16 %4, ptr %3, align 2, !tbaa !3
  store i16 1, ptr %2, align 2, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 2, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 2, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"short", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
